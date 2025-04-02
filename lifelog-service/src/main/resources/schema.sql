create table lifelog (

    id uuid primary key,  -- 로그 고유 ID
    user_id varchar(100) not null, -- 유저 아이디
    ci varchar(255) not null, -- 유저 식별자
    is_active boolean not null default true,
    log_type varchar(50) not null, -- BLOOD_PRESSURE 등
    payload jsonb not null, -- N개의 데이터가 추가될 수 있기 때문에 JSON으로 저장.
    status varchar(20) default 'NORMAL', -- 정상, 경고, 위험 상태값
    start_time TIMESTAMP not null, -- 활동 시작 시간
    created_at TIMESTAMP not null default now(),
    updated_at TIMESTAMP not null default now()

);