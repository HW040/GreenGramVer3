package green.greengram.Feed.model;

import green.greengram.Feed.comment.model.FeedCommentGetRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FeedGetRes {
    private long writerUserId;
    private long feedId;
    private String contents;
    private String location;
    private String createdAt;
    private String writerNm;
    private String writerPic;
    private int isLike;

    private List<String> pics;
    private FeedCommentGetRes comment;




}
