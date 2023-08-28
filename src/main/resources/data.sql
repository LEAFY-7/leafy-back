INSERT INTO user(user_id, created_at, modified_at, is_delete, email, name, nick_name, password, phone, user_role)
VALUES ( 1, now(), now(), false, 'string@naver.com', '테스트', 'test1', '$2a$10$48fbkW2.Rt2i9Jyp5a9jPeMNLSMHxOCWo3DSnoiDw.vnsbKaKWkqG', '000000000', 'MEMBER' ),
       ( 2, now(), now(), false, 'string@gmail.com', '테스트', 'test2','$2a$10$48fbkW2.Rt2i9Jyp5a9jPeMNLSMHxOCWo3DSnoiDw.vnsbKaKWkqG', '000000000', 'ADMIN' ),
       ( 3, now(), now(), false, 'string@daum.com', '테스트', 'test3','$2a$10$48fbkW2.Rt2i9Jyp5a9jPeMNLSMHxOCWo3DSnoiDw.vnsbKaKWkqG', '000000000', 'ADMIN' );