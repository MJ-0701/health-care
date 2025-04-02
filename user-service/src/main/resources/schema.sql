create table users (
    user_id VARCHAR(255) primary key, -- UID + 년월일 + uuid 조합으로 생성
    user_name VARCHAR(100) not null , -- 외국인을 고려하여 100으로 설정
    email VARCHAR(100) not null unique, -- 사용자 이메일
    birth_date DATE not null, -- 생년월일
    gender CHAR(1), -- 성별
    ci VARCHAR(255) default null,  -- 고유 CI값 본인 인증 후 업데이트,
    last_login_at DATETIME DEFAULT NULL, -- 마지막 로그인 시각
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 생성 시각
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정 시각

);