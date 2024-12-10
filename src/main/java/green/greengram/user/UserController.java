package green.greengram.user;

import green.greengram.common.model.ResultResponse;
import green.greengram.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@Tag(name = "유저", description = "회원가입, 로그인")
public class UserController {
    private final UserService service;

    @PostMapping("sign-up")
    @Operation(summary = "회원 가입")
    public ResultResponse<Integer> signUp(@RequestPart UserSignUpReq p //파일이 여러개면 List<>들어간다
            , @RequestPart(required = false) MultipartFile pic) {
        return ResultResponse.<Integer>builder()
                .resultMessage("가입 완료")
                .resultData(service.postSignUp(pic, p))
                .build();

    }

    @PostMapping("sign-in")
    @Operation(summary = "로그인")
    public ResultResponse<UserSignInRes> signIn(@RequestBody UserSignInReq p){
        UserSignInRes res = service.postSignIn(p);
        return ResultResponse.<UserSignInRes>builder()
                .resultMessage(res.getMessage())
                .resultData(res)
                .build();
    }

    @GetMapping
    @Operation(summary = "유저 프로필 정보")
    public ResultResponse<UserInfoGetRes> getUserInfo(@ParameterObject UserInfoGetReq p){
        log.info("userController>UserInfoGetReq:{}",p);
        UserInfoGetRes res = service.getUserInfo(p);
        return ResultResponse.<UserInfoGetRes>builder()
                .resultMessage("유저 프로필 정보")
                .resultData(res)
                .build();
    }

    @PatchMapping("pic")
    public ResultResponse<String> patchProfilePic(UserPicPatchReq p) {
        return ResultResponse.<String>builder()
                .resultMessage("프로필 사진 변경 완료")
                .resultData(service.patchUserPic(p))
                .build();
    }

}
