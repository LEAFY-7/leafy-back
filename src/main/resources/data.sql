INSERT INTO user(user_id, created_at, modified_at, is_delete, email, nick_name, password, phone, user_role)
VALUES (
           1, now(), now(), false, 'string@naver.com', '테스트', '$2a$10$48fbkW2.Rt2i9Jyp5a9jPeMNLSMHxOCWo3DSnoiDw.vnsbKaKWkqG', '000000000', 'MEMBER'
       ),
       (
           2, now(), now(), false, 'string@gmail.com', '테스트', '$2a$10$48fbkW2.Rt2i9Jyp5a9jPeMNLSMHxOCWo3DSnoiDw.vnsbKaKWkqG', '000000000', 'ADMIN'
       );

INSERT INTO address(created_at, modified_at, is_delete, detail, is_hide, lot, reference, street, zipcode, user_id)
VALUES (
            now(), now(), false, 'test1', false, "test1",  "test1",  "test1", "test1",  1
       ),
       (
           now(), now(), false, 'test2', false, "test2",  "test2",  "test2",  "test2",  2
       );
