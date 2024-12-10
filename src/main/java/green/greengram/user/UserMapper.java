package green.greengram.user;

import green.greengram.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserSignUpReq p);
     UserSignInRes selUserForSignIn(UserSignInReq p);
     UserInfoGetRes selUserInfo(UserInfoGetReq p);
     UserInfoGetRes selUserInfo2(UserInfoGetReq p);

     int updUserPic(UserPicPatchReq p);
}
