create table if not exists `version`
(
    `id` int auto_increment primary key,
    `mod_id` int,
    `name` varchar(128),
    `download_Url` varchar(1024),
     constraint tutorial__mod_id__uindex unique (`mod_id`)
);

create table if not exists `tag`
(
    `id` int auto_increment primary key,
    `mod_id` int,
    `name` varchar(128),
    `description` varchar(1024),
     constraint tutorial__mod_id__uindex unique (`mod_id`)
);

# A link to a tutorial about the mod
create table if not exists tutorial
(
    `id` int auto_increment primary key,
    `mod_id` int,
    `description` varchar(512),
    `url` varchar(128),
     constraint tutorial__mod_id__uindex unique (`mod_id`)
);

create table if not exists `mod_alternative`
(
    id int auto_increment primary key,
    mod_id int,
    mod_alternative_id int,
    constraint mod_alternative_mod__fk foreign key (mod_id) references `mod` (id),
    constraint mod_alternative_mod_alternative__fk foreign key (mod_alternative_id) references `mod` (id)
);

# create table if not exists `mod_version`
# (
#     id int auto_increment primary key,
#     mod_id int,
#     version_id int,
#     constraint mod_version__fk foreign key (mod_id) references `mod` (id),
#     constraint version_id_version__fk foreign key (version_id) references `version` (id)
# );

# create table if not exists `mod_tag`
# (
#     id int auto_increment primary key,
#     mod_id int,
#     tag_id int,
#     constraint mod_tag__fk foreign key (mod_id) references `mod` (id),
#     constraint mod_tag_tag__fk foreign key (tag_id) references `tag` (id)
# );

# create table if not exists mod_tutorial
# (
#     id int auto_increment primary key,
#     mod_id int,
#     source_id int,
#     constraint mod_source__fk foreign key (mod_id) references `mod` (id),
#     constraint mod_source_source__fk foreign key (source_id) references tutorial (id)
# );

create table if not exists `mod`
(
    `id` int auto_increment primary key,
    `curseforge_identifier` int,
#     `mod_version_id` int,
    `name` varchar(256),
    `description` text,
    `website_url` varchar(1024),
    `source_code_url` varchar(1024),
    `game_version` varchar(64),
    `side` enum('CLIENT','BOTH','SERVER'),
    `bugs_speculation` int,
    `pregeneration_required` boolean,
    `works_in_singleplayer` boolean
#     `added_presentation` boolean,
#     `ready` boolean,
#     `comment` text
);

create table if not exists `mod_dependency`
(
    id int auto_increment primary key,
    mod_id int,
    mod_dependency_version_id int,
#     mod_dependency_version_id int,
    constraint mod_dependency_mod__fk foreign key (mod_id) references `mod` (id),
    constraint mod_dependency_version__fk foreign key (mod_dependency_version_id) references `version` (id)
#     constraint mod_dependency_mod_version__fk foreign key (mod_dependency_version_id) references `mod_version` (id)
);