package kh.semiProject.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Attachment {
	private int attachmentId;
	private String attachmentName;
	private Date attachmentDate;
	private int postNo;
	private String attmStatus;
	private String attmRename;
	private String attmPath;
}
