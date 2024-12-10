package green.greengram.Feed;

import green.greengram.Feed.model.FeedPicDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedPicsMapper {
    int insFeedPic(FeedPicDto p);
    int insFeedPic2(FeedPicDto p);
    List<String> selFeedPicList(long feedId);
}
