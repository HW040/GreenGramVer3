package green.greengram.Feed.comment.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeedCommentGetRes{

    // 1. 피드 리스트 가져올 때 3개의 (최신)댓글 가져온다
    // 2. 댓글 더보기 눌렀을 때 가져오는 댓글들
    @Schema(title = "피드 댓글 더보기 여부")
    private boolean moreComment;
    @Schema(title = "피드 댓글 리스트")
    private List<FeedCommentDto> commentList;




}


