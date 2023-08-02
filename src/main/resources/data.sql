INSERT INTO user(user_id, created_at, modified_at, is_delete, email, nick_name, password, phone, user_role, gender, birth_day)
VALUES (
           1, now(), now(), false, 'string@naver.com', '테스트', '$2a$10$48fbkW2.Rt2i9Jyp5a9jPeMNLSMHxOCWo3DSnoiDw.vnsbKaKWkqG', '000000000', 'MEMBER' , 'MALE', "2023-07-12"
       ),
       (
           2, now(), now(), false, 'string@gmail.com', '테스트', '$2a$10$48fbkW2.Rt2i9Jyp5a9jPeMNLSMHxOCWo3DSnoiDw.vnsbKaKWkqG', '000000000', 'ADMIN' , 'FEMALE', "2023-07-13"
       );

INSERT INTO address(created_at, modified_at, is_delete, detail_address, is_hide, road_address, jibun_address, address, zone_code, user_id)
VALUES (
            now(), now(), false, 'test1', false, "test1",  "test1",  "test1", "test1",  1
       ),
       (
           now(), now(), false, 'test2', false, "test2",  "test2",  "test2",  "test2",  2
       );
