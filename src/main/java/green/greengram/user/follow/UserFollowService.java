package green.greengram.user.follow;

import green.greengram.user.follow.model.UserFollowReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFollowService {
    private final UserFollowMapper mapper;

    public int Follow(UserFollowReq p){
        return mapper.postFollow(p);
    }

    public int UnFollow(UserFollowReq p){
        return mapper.deleteFollow(p);
    }
}
