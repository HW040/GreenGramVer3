package green.greengram.Feed.like;

import green.greengram.Feed.like.model.FeedLikeReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedLikeMapper {
    int insFeedLike(FeedLikeReq p);
    int delFeedLike(FeedLikeReq p);
}
