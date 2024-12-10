package green.greengram.user.follow;

import green.greengram.common.model.ResultResponse;
import green.greengram.user.follow.model.UserFollowReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("user/follow")
@RestController
@RequiredArgsConstructor
public class UserFollowController {
    private final UserFollowService service;

    @PostMapping
    public ResultResponse<Integer> postUserFollow(@RequestBody UserFollowReq p){
        log.info("Posting user follow req: {}", p);
        return ResultResponse.<Integer>builder()
                .resultMessage("Follow")
                .resultData(service.Follow(p))
                .build();
    }



    @DeleteMapping
    public ResultResponse<Integer> deleteUserFollow(@ParameterObject UserFollowReq p){
        log.info("Deleting user follow req: {}", p);
        return ResultResponse.<Integer>builder()
                .resultMessage("unFollow")
                .resultData(service.UnFollow(p))
                .build();
    }
}
