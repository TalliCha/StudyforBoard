# board table 만들기

#tbl_upload : 파일 저장
#tbl_board : 게시글 저장


DELIMITER $$ 
DROP PROCEDURE IF EXISTS CREATE_TABLES$$
CREATE PROCEDURE CREATE_TABLES()
BEGIN
	DROP TABLE IF EXISTS tbl_upload;
    DROP TABLE IF EXISTS tbl_comment;
	DROP TABLE IF EXISTS tbl_board;
    
	
	CREATE TABLE tbl_board (
	  bno int(11) NOT NULL
	  ,title varchar(80) NOT NULL
	  ,content text NOT NULL
	  ,writer varchar(80)
	  ,regdate datetime DEFAULT now()
	  ,viewcnt int DEFAULT 0
	  ,upload_file int DEFAULT 0
	  ,ref_bno int(11)
	  ,reply_bno int(11)
	  ,reply_lv int(11)
      ,parent_bno int(11)
	  , PRIMARY KEY (bno)
	  )ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;
	  
	  
	  CREATE TABLE tbl_upload (
	  fno int(11) NOT NULL AUTO_INCREMENT
	  ,bno int(11) NOT NULL
	  ,original_fname varchar(60) NOT NULL
	  ,upload_fname varchar(96) NOT NULL
	  ,fsize int(10) not null
	  ,ftype varchar(40) not null
	  ,PRIMARY KEY (fno)
	  ,CONSTRAINT fk_upload_bno 
		FOREIGN KEY (bno) REFERENCES tbl_board (bno) 
			ON DELETE CASCADE 
	  )ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;
      
      CREATE TABLE tbl_comment (
	  cno int(11) NOT NULL
	  ,bno int(11) NOT NULL
	  ,content text NOT NULL
	  ,writer varchar(80)
	  ,regdate datetime DEFAULT now()
	  ,ref_cno int(11)
	  ,reply_cno int(11)
	  ,reply_lv int(11)
      ,parent_cno int(11)
	  , PRIMARY KEY (cno)
	  ,CONSTRAINT fk_comment_bno 
		FOREIGN KEY (bno) REFERENCES tbl_board (bno) 
			ON DELETE CASCADE
	  )ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;
END$$
DELIMITER $$

CALL CREATE_TABLES();



