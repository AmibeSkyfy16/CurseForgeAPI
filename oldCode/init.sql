create table if not exists asset
(
    id int auto_increment primary key,
    icon_url varchar(1024),
    tile_url varchar(1024),
    cover_url varchar(1024)
    );

create table if not exists game
(
    id int auto_increment primary key,
    asset_id int,
    name varchar(512),
    slug varchar(512),
    date_modified datetime,
    status int,
    api_status int,
    constraint game_asset__fk foreign key (asset_id) references asset (id)
    );

create table if not exists version_type
(
    id int auto_increment primary key,
    game_id int,
    name varchar(512),
    slug varchar(512),
    constraint version_type_game_fk foreign key (game_id) references game (id)
    );

create table if not exists version
(
    id int auto_increment primary key,
    version_type_id int,
    name varchar(512),
    constraint version_version_type__fk foreign key (version_type_id) references version_type (id)
    );

create table if not exists category
(
    id int auto_increment primary key,
    parent_category_id int,
    class_id int,
    name varchar(512),
    slug varchar(512),
    url varchar(512),
    icon_url varchar(1024),
    date_modified datetime,
    is_class boolean,
    display_index int,
    constraint category_category__fk foreign key (parent_category_id) references category (id)
    );

create table if not exists `link`
(
    id int auto_increment primary key,
    website_url varchar(1024),
    wiki_url varchar(1024),
    issues_url varchar(1024),
    source_url varchar(1024)
    );

create table if not exists `author`
(
    id int auto_increment primary key,
    name varchar(512),
    url varchar(1024)
    );

create table if not exists `logo`
(
    id int auto_increment primary key,
    title varchar(512),
    description varchar(512),
    thumbnail_url varchar(1024),
    url varchar(1024)
    );

create table if not exists `screenshot`
(
    id int auto_increment primary key,
    title varchar(512),
    description varchar(512),
    thumbnail_url varchar(1024),
    url varchar(1024)
    );

create table if not exists `hash`
(
    id int auto_increment primary key,
    value varchar(256),
    algo int
    );

create table if not exists `module`
(
    id int auto_increment primary key,
    name varchar(256),
    fingerprint int
    );

create table if not exists `mod`
(
    id int auto_increment primary key,
    game_id int,
    logo_id int,
    link_id int,
    primary_category_id int,
    class_id int,
    name varchar(512),
    slug varchar(512),
    summary varchar(512),
    status int,
    download_count int default 0,
    is_featured boolean,
    date_created datetime,
    date_modified datetime,
    date_released datetime,
    allow_mod_distribution boolean,
    game_popularity_rank int default 0,
    is_available boolean,
    thumbs_up_count int default 0,
    constraint mod_game__fk foreign key (game_id) references game (id),
    constraint mod_logo__fk foreign key (logo_id) references logo (id),
    constraint mod_link__fk foreign key (link_id) references link (id),
    constraint mod_category__fk foreign key (primary_category_id) references category (id)
    );

create table if not exists `file`
(
    id int auto_increment primary key,
    game_id int,
    mod_id int,
    parent_project_file_id int,
    alternate_file_id int,
    is_available boolean,
    display_name varchar(512),
    file_name varchar(512),
    release_type int,
    file_status int,
    file_date datetime,
    file_length int,
    download_count int default 0,
    download_url varchar(1024),
    expose_as_alternative boolean,
    is_server_pack boolean,
    file_finger_print int,
    constraint file_game__fk foreign key (game_id) references `game` (id),
    constraint file_mod__fk foreign key (mod_id) references `mod` (id),
    constraint file_file__fk foreign key (parent_project_file_id) references `file` (id),
    constraint file2_file__fk foreign key (alternate_file_id) references `file` (id)

    );

create table if not exists `file_module`
(
    id int auto_increment primary key,
    file_id int,
    module_id int,
    constraint file_module_file__fk foreign key (file_id) references `file` (id),
    constraint file_module_module__fk foreign key (module_id) references `module` (id)
    );

#TODO sortableGameVersions

create table if not exists `file_game_version`
(
    id int auto_increment primary key,
    file_id int,
    version_id int,
    constraint file_game_version_file__fk foreign key (file_id) references `file` (id),
    constraint file_game_version_version__fk foreign key (version_id) references `version` (id)
    );

create table if not exists `file_hash`
(
    id int auto_increment primary key,
    file_id int,
    hash_id int,
    constraint file_hash_file__fk foreign key (file_id) references `file` (id),
    constraint file_hash_hash__fk foreign key (hash_id) references `hash` (id)
    );

create table if not exists `dependency`
(
    id int auto_increment primary key,
    mod_id int,
    relation_type int,
    constraint dependency_mod__fk foreign key (mod_id) references `mod` (id)
    );

create table if not exists `file_dependency`
(
    id int auto_increment primary key,
    file_id int,
    dependency_id int,
    constraint file_dependency_file__fk foreign key (file_id) references `file` (id),
    constraint file_dependency_dependency__fk foreign key (dependency_id) references `dependency` (id)
    );

create table if not exists `mod_author`
(
    id int auto_increment primary key,
    mod_id int,
    author_id int,
    constraint mod_author_mod__fk foreign key (mod_id) references `mod` (id),
    constraint mod_author_author__fk foreign key (author_id) references `author` (id)
    );

create table if not exists `mod_screenshot`
(
    id int auto_increment primary key,
    mod_id int,
    screenshot_id int,
    constraint mod_screenshot_mod__fk foreign key (mod_id) references `mod` (id),
    constraint mod_screenshot_screenshot__fk foreign key (screenshot_id) references `screenshot` (id)
    );

create table if not exists `mod_category`
(
    id int auto_increment primary key,
    game_id int,
    mod_id int,
    category_id int,
    constraint mod_category_game__fk foreign key (game_id) references `game` (id),
    constraint mod_category_mod__fk foreign key (mod_id) references `mod` (id),
    constraint mod_category_category__fk foreign key (category_id) references `category` (id)
    );