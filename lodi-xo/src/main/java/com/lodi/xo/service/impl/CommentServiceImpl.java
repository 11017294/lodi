package com.lodi.xo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.convert.comment.CommentConvert;
import com.lodi.common.model.entity.Article;
import com.lodi.common.model.entity.Comment;
import com.lodi.common.model.entity.User;
import com.lodi.common.model.request.comment.CommentAddRequest;
import com.lodi.common.model.request.comment.CommentPageRequest;
import com.lodi.common.model.request.comment.CommentQueryRequest;
import com.lodi.common.model.request.comment.CommentUpdateRequest;
import com.lodi.common.model.vo.CommentTreeVO;
import com.lodi.common.model.vo.CommentVO;
import com.lodi.common.mybatis.page.PageRequest;
import com.lodi.common.mybatis.service.impl.BaseServiceImpl;
import com.lodi.common.satoken.utils.LoginHelper;
import com.lodi.xo.mapper.ArticleMapper;
import com.lodi.xo.mapper.CommentMapper;
import com.lodi.xo.service.CommentService;
import com.lodi.xo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.lodi.common.core.constant.StatusConstant.OFF;

/**
 * 评论 服务层实现
 *
 * @author MaybeBin
 * @createDate 2024-04-03
 */
@Slf4j
@Service
@AllArgsConstructor
public class CommentServiceImpl extends BaseServiceImpl<CommentMapper, Comment> implements CommentService {

    private final UserService userService;
    private final ArticleMapper articleMapper;

    @Override
    public Boolean insertComment(CommentAddRequest addRequest) {
        Comment comment = CommentConvert.INSTANCE.toEntity(addRequest);
        Long userId = LoginHelper.getUserId();
        // 1.用户是否开启评论
        User user = userService.getById(userId);
        if (user.getCommentStatus().equals(OFF)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "用户未开启评论");
        }
        // 2.是文章评论
        if ("ARTICLE".equals(comment.getSource())) {
            Long articleId = comment.getArticleId();
            Article article = articleMapper.selectById(articleId);
            // 2.1文章不存在
            if (Objects.isNull(article)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "文章不存在");
            }
            // 2.2文章未开启评论
            if (article.getOpenComment().equals(OFF)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "文章未开启评论");
            }
        }
        // 3.是回复
        if (Objects.nonNull(comment.getToId())) {
            Comment toComment = this.getById(comment.getToId());
            // 3.1评论不存在
            if (Objects.isNull(toComment)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "回复的评论不存在");
            }
            // 3.2回复对象不存在
            Long toUserId = toComment.getUserId();
            User toUser = userService.getById(toUserId);
            if (Objects.isNull(toUser)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "回复的用户不存在");
            }
            // 设置一级评论ID
            Long firstCommentId = toComment.getFirstCommentId() == null ? toComment.getId() : toComment.getFirstCommentId();
            comment.setFirstCommentId(firstCommentId);
            // 设置被回复人id
            comment.setToUserId(toUserId);
        }
        // 4.设置评论人id
        comment.setUserId(userId);
        return save(comment);
    }

    @Override
    public Page<CommentVO> getCommentByCurrentUser(PageRequest pageRequest) {
        Long userId = LoginHelper.getUserId();
        // 构建分页
        Page<Comment> page = new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        // 构建条件
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getUserId, userId);
        // 获取用户评论
        Page<Comment> commentPage = baseMapper.selectPage(page, queryWrapper);
        Page<CommentVO> commentVOPage = CommentConvert.INSTANCE.toVO(commentPage);
        List<CommentVO> commentVOList = commentVOPage.getRecords();

        // 设置基本信息
        String username = LoginHelper.getUsername();
        String userAvatar = LoginHelper.getLoginUser().getUserAvatar();
        for (CommentVO commentVO : commentVOList) {
            commentVO.setUsername(username);
            commentVO.setUserAvatar(userAvatar);
        }
        return commentVOPage;
    }

    @Override
    public Boolean updateComment(CommentUpdateRequest updateRequest) {
        Comment comment = CommentConvert.INSTANCE.toEntity(updateRequest);
        return updateById(comment);
    }

    @Override
    public Boolean deleteComment(Long id) {
        // 检查是否是当前用户或管理员
        checkCurrentUserOrAdmin(id);
        int count = baseMapper.deleteCommentAndChildren(id);
        log.info("删除评论，id:{}，删除{}条", id, count);
        return true;
    }

    /**
     * 检查是否当前用户或管理员
     *
     * @param commentId 评论id
     */
    private void checkCurrentUserOrAdmin(Long commentId) {
        if (commentId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Comment comment = getById(commentId);
        if (comment == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        LoginHelper.checkLoginUserOrAdmin(comment.getUserId());
    }

    @Override
    public Page<Comment> getCommentPage(CommentPageRequest pageRequest) {
        Page<Comment> page = new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<Comment> queryWrapper = buildQueryWrapper(pageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    private LambdaQueryWrapper<Comment> buildQueryWrapper(CommentPageRequest pageRequest) {
        return new LambdaQueryWrapper<Comment>();
    }

    @Override
    public Page<CommentTreeVO> getCommentTreeVOPage(CommentQueryRequest queryRequest) {
        Long articleId = queryRequest.getArticleId();
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(articleId), Comment::getArticleId, articleId)
                .eq(Comment::getSource, queryRequest.getSource())
                .orderByDesc(Comment::getCreateTime)
                .isNull(Comment::getFirstCommentId);
        // 得到一级评论
        Page<Comment> queryPage = new Page<>(queryRequest.getCurrentPage(), queryRequest.getPageSize());
        Page<Comment> commentPage = baseMapper.selectPage(queryPage, queryWrapper);
        List<Comment> commentList = commentPage.getRecords();
        // 没有评论直接返回
        if (commentList.isEmpty()) {
            return CommentConvert.INSTANCE.toTreeVO(commentPage);
        }
        // 一级评论id
        List<Long> firstIdList = commentList.stream().map(comment -> comment.getId()).collect(Collectors.toList());
        // 获取二级评论
        LambdaQueryWrapper<Comment> secondQueryWrapper = new LambdaQueryWrapper<>();
        secondQueryWrapper.in(Comment::getFirstCommentId, firstIdList);
        List<Comment> secondCommentList = baseMapper.selectList(secondQueryWrapper);

        // 得到用户id
        List<Long> userIdList = commentList.stream().map(comment -> comment.getUserId()).collect(Collectors.toList());
        // 获取二级评论的相关用户信息
        if (!secondCommentList.isEmpty()) {
            List<Long> secondUserIdList = secondCommentList.stream().map(comment -> comment.getUserId()).collect(Collectors.toList());
            List<Long> secondToUserIdList = secondCommentList.stream().map(comment -> comment.getToUserId()).collect(Collectors.toList());
            userIdList.addAll(secondUserIdList);
            userIdList.addAll(secondToUserIdList);
            commentList.addAll(secondCommentList);
        }
        // 获取所有相关用户的信息
        List<User> secondUserList = userService.listByIds(userIdList);
        Map<Long, User> userMap = secondUserList.stream().collect(Collectors.toMap(User::getId, user -> user));

        Page<CommentTreeVO> treeVOPage = CommentConvert.INSTANCE.toTreeVO(commentPage);
        List<CommentTreeVO> treeVOList = CommentConvert.INSTANCE.toTreeVO(commentList);
        for (CommentTreeVO commentTreeVO : treeVOList) {
            // 设置用户信息
            User user = userMap.get(commentTreeVO.getUserId());
            commentTreeVO.setUsername(user.getNickname());
            commentTreeVO.setUserAvatar(user.getUserAvatar());
            // 设置被回复用户信息
            User toUser = userMap.getOrDefault(commentTreeVO.getToUserId(), new User());
            commentTreeVO.setToUsername(toUser.getNickname());
            commentTreeVO.setToUserAvatar(toUser.getUserAvatar());
        }
        // 按一级评论id分组，得到二级评论map
        Map<Long, List<CommentTreeVO>> secondCommentTreeMap = treeVOList.stream()
                .filter(commentTreeVO -> Objects.nonNull(commentTreeVO.getFirstCommentId()))
                .collect(Collectors.groupingBy(CommentTreeVO::getFirstCommentId));

        List<CommentTreeVO> records = treeVOList.stream()
                .filter(commentTreeVO -> Objects.isNull(commentTreeVO.getFirstCommentId()))
                .map(commentTreeVO -> {
                    commentTreeVO.setChildren(secondCommentTreeMap.getOrDefault(commentTreeVO.getId(), new ArrayList<>()));
                    return commentTreeVO;
                }).collect(Collectors.toList());

        return treeVOPage.setRecords(records);
    }
}
