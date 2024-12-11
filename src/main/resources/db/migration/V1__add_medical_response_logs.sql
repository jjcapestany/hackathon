create table medical_response_logs
(
    log_id        integer not null
        constraint medical_response_logs_pk
            primary key,
    team_name     text,
    location      text,
    cases_treated integer,
    case_type     text,
    supplies_used text,
    status        text,
    timestamp     text,
    notes         text
);