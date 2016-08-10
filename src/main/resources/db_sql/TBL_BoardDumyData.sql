 
#insert db 1000 / 20종

DELIMITER $$
DROP PROCEDURE IF EXISTS ADD_DUMMY_DATA$$
CREATE PROCEDURE ADD_DUMMY_DATA()
BEGIN
	DECLARE i INT DEFAULT 1;
    DECLARE j INT DEFAULT 1;
	DECLARE title VARCHAR(80);	
	DECLARE content TEXT;	
	DECLARE writer VARCHAR(80);	
    
	WHILE i <= 100 DO
		SET title = CONCAT(i%20,"title");
        SET content = CONCAT(i%20,"content");
        SET writer = CONCAT(i%20,"writer");
		INSERT INTO `tbl_board`
			(`bno`,`title`,`content`,`writer`,`ref_bno`,`reply_bno`,`reply_lv`)
				select (COALESCE(MAX(bno),0)+1),title,content,writer, (COALESCE(MAX(bno),0)+1), 0, 0 from tbl_board;
		SET i = i + 1;
	END WHILE;
    
    	WHILE j <= 100 DO
        SET content = CONCAT(i%20,"content");
        SET writer = CONCAT(i%20,"writer");
		INSERT INTO `tbl_comment`
			(`cno`,`bno`,`content`,`writer`,`ref_cno`,`reply_cno`,`reply_lv`)
				select (COALESCE(MAX(cno),0)+1), 99 ,content,writer, (COALESCE(MAX(cno),0)+1), 0, 0 from tbl_comment;
		SET j = j + 1;
	END WHILE;
END$$
DELIMITER $$

CALL ADD_DUMMY_DATA();




    
    
