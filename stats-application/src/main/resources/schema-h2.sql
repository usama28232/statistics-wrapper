create table stats_builder
(
    s_org           varchar(70)  not null,
    s_view          varchar(100) not null,
    aggregate_field varchar(255),
    aggregate_func  varchar(255),
    custom_agg      varchar(255),
    filter          varchar(255),
    max_rows        integer,
    selection       varchar(255),
    user_id         varchar(255),
    active          varchar(1)   not null default 'Y',
    add_dtm         timestamp    not null,
    add_by          varchar(255)          default 'H2',
    chg_dtm         timestamp    not null,
    chg_by          varchar(255)          default 'H2',
    primary key (s_org, s_view)
);

create table census
(
    c_year     varchar(10)  not null,
    country    varchar(100) not null,
    state      varchar(100) not null,
    city       varchar(100) not null,
    district   varchar(100) not null,
    population numeric(22,8),
    density    numeric(22,8),
    area       numeric(22,8),
    active     varchar(1)   not null default 'Y',
    add_dtm    timestamp    not null,
    add_by     varchar(255)          default 'H2',
    chg_dtm    timestamp    not null,
    chg_by     varchar(255)          default 'H2',
    primary key (c_year, state, country, city, district)
)
