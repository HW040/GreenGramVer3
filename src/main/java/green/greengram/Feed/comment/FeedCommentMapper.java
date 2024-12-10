package green.greengram.Feed.comment;

import green.greengram.Feed.comment.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    void insFeedComment(FeedCommentPostReq p);
    List<FeedCommentDto> selFeedCommentList(FeedCommentGetReq p);
    int delFeedComment(FeedCommentDelReq p);
}


