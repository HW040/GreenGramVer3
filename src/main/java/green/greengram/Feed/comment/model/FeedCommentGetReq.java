package green.greengram.Feed.comment.model;

import green.greengram.common.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

/*
1, 피드 리스트 가져올 때 3개의 댓글 가져온다
 isMore
 1 > 0,4

 2. 댓글 더보기 눌렀을 때 가져오는 댓글들
 2 > 3,21
 3 > 23,21
 4 > 43,21
 5 > 63,21
 >> delete 후 더보기를 하려면 위의 개념은 무너진다.
 */
@Getter
@Schema(title = "피드 댓글 리스트 요청")
@ToString
public class FeedCommentGetReq {
    private final static int FIRST_COMMENT_SIZE = 3;

    @Schema(title="피드 PK", description = "피드 PK", name="feed_id", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long feedId;

    @Schema(title="튜플 시작 index", description = "댓글 Element 갯수를 보내주면 된다.", name="start_idx", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private int startIdx;

    @Schema(title="페이지 당 아이템 수", description = "default: 20", example = "20")
    private int size;

    @ConstructorProperties({"feed_id", "start_idx", "size"})
    public FeedCommentGetReq(long feedId, int startIdx, Integer size) {
        this.feedId = feedId;
        this.startIdx = startIdx;
        this.size = (size == null ? Constants.getDefault_page_size() : size) + 1;
    }

}
