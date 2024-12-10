package green.greengram.user;

import green.greengram.common.MyFileUtils;
import green.greengram.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final MyFileUtils myFileUtils;
    private final UserMapper userMapper;

    public int postSignUp(MultipartFile pic, UserSignUpReq p) {
        //프로필 이미지 파일 처리
        String savedPicName = (pic != null ? myFileUtils.makeRandomFileName(pic) : null);
        p.setPic(savedPicName);

        // String savedPicName = myFileUtils.makeRandomFileName(pic.getOriginalFilename());
        //위와 같은 결과가 나왔으면 좋겠다

        String hashedPassword = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt());
        log.info("hashedPassword: {}", hashedPassword);
        p.setUpw(hashedPassword);

        int result = mapper.insUser(p);

        if(pic == null) {
            return result;
        }
        //D:/2024-02/download/greengram_ver2/user/#{userId}/savedPicName(사진파일명.확장자)
        long userId = p.getUserId(); //userId를  insert 후에 얻을 수 있다.
        String middlePath = String.format("user/%d", userId);
        myFileUtils.makeFolders(middlePath);

        String filePath = String.format("%s/%s", middlePath, savedPicName);
        try {
            myFileUtils.transferTo(pic, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    public UserSignInRes postSignIn(UserSignInReq p) {
        UserSignInRes res = mapper.selUserForSignIn(p);
        if (res == null) {  // null 아이디가 없다는 뜻
            res = new UserSignInRes();
            res.setMessage("아이디를 확인해 주세요.");
            return res;
        }
        if( !BCrypt.checkpw(p.getUpw(), res.getUpw())) { //! 비밀번호가 다를시
            res = new UserSignInRes();
            res.setMessage("비밀번호를 확인해 주세요");
            return res;
        }
        res.setMessage("로그인 성공");
        return res;

    }

    public UserInfoGetRes getUserInfo(UserInfoGetReq p) {
        return mapper.selUserInfo2(p);
    }

    public String patchUserPic(UserPicPatchReq p) {
        // 저장할 팔일명(랜덤한 파일명) 생성한다. 이때, 확장자는 오리지날 파일명과 일치하게 한다.
        String savedPicName = (p.getPic() != null ? myFileUtils.makeRandomFileName(p.getPic()) : null);

        // 기존 팔일 삭제(방법 3가지 [1]: 폴더를 지운다. [2] select해서 기존 파일명을 얻어온다. [3]기존 파일명을 FE에서 받는다.)
        String deletePath = String.format("%s/user/%d", myFileUtils.getUploadPath(), p.getSignedUserId());
        myFileUtils.deleteFolder((deletePath), false);

        /*File fi= new File(deletePath);
        if(fi.exists()) {
            myFileUtils.deleteFolder(deletePath, false);
        }else {
            myFileUtils.makeFolders(String.format("user/%d/%s", p.getSignedUserId(), savedPicName));
        }*/


        // 원하는 위치에 저장할 파일명으로 파일을 이동(transferTo)한다
        String filePath = String.format("user/%d/%s", p.getSignedUserId(), savedPicName);
        try {
            myFileUtils.transferTo(p.getPic(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // DB에 튜플을 수정(Update)한다
        p.setPicName(savedPicName);
        int result = mapper.updUserPic(p);
        return savedPicName;
    }

}
