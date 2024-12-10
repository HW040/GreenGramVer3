package green.greengram.user.follow;

import green.greengram.user.follow.model.UserFollowReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFollowMapper {

    int postFollow(UserFollowReq p);
    int deleteFollow(UserFollowReq p);
}
