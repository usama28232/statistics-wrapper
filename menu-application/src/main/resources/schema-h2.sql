create table menu
(
    org     varchar(70)  not null,
    request varchar(100) not null,
    active  varchar(1)   not null default 'Y',
    add_dtm timestamp    not null,
    add_by  varchar(255)          default 'H2',
    chg_dtm timestamp    not null,
    chg_by  varchar(255)          default 'H2',
    primary key (org, request)
);

create table menu_detail
(
    org     varchar(70)  not null,
    request varchar(100) not null,
    lang    varchar(255) not null,
    text    varchar(255),
    title   varchar(255),
    active  varchar(1)   not null default 'Y',
    add_dtm timestamp    not null,
    add_by  varchar(255)          default 'H2',
    chg_dtm timestamp    not null,
    chg_by  varchar(255)          default 'H2',
    primary key (org, request, lang)
);

create table menu_stats_mapping
(
    org     varchar(70)  not null,
    request varchar(100) not null,
    s_org   varchar(70)  not null,
    s_view  varchar(100) not null,
    active  varchar(1)   not null default 'Y',
    add_dtm timestamp    not null,
    add_by  varchar(255)          default 'H2',
    chg_dtm timestamp    not null,
    chg_by  varchar(255)          default 'H2',
    primary key (org, request, s_org, s_view)
);