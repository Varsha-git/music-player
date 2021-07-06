use jukebox;
create table album
(
album_id int not null primary key,
released_year int not null,
album_name varchar(40) not null
);
insert into album values(11,2014,'1989');
insert into album values(12,2011,'when sun goes down');
insert into album values(13,2007,'swades');
insert into album values(14,2015,'tamasha');
insert into album values(15,2019,'migraine');
insert into album values(16,2020,'earth vocano plates');
insert into album values(17,2018,'time clock calender');
insert into album values(18,2020,'human music communication');
select * from album;

create table song
(
song_id int not null primary key,
song_name varchar(100) not null,
location varchar(200) not null,
duration varchar(20) not null,
album_id int not null,
genre varchar(30) not null,
artist varchar(40) not null,
constraint fk_album foreign key(album_id) references album(album_id)
);

insert into song values(1,'blank space','D:\songs\lankspace.wav','4:08',11,'Pop','Taylor Swift');
insert into song values(2,'Who says','D:\songs\who says.wav','5:08',12,'Pop','Selena Gomez');
insert into song values(3,'Uhi chala chal rahi','D:\songs\who says.wav','6:12',13,'Travelling','Udit Narayan');
insert into song values(4,'safarnama','D:\songs\who says.wav','5:20',14,'Sufi','Lucky Ali');
update song set location='D:\\songs\\yuh hi chala chal.wav' where song_id=3;

select * from song;
drop table podcast;

create table podcast
(
podcast_id int not null primary key,
podcast_title varchar(100) not null,
celebrity_name varchar(100),
duration varchar(20) not null,
location varchar(200) not null,
album_id int not null,
genre varchar(50) not null,
constraint fk_album_id foreign key(album_id) references album(album_id)
);

insert into podcast values(1,'Have a Headache? You are not alone !','Steve Ember','15:20','D:\\podcast\\headache.wav',15,'medical');
insert into podcast values(2,'Earth is always on the move','Kelly Jean','15:30','D:\\podcast\\earthmove.wav',16,'geography');
insert into podcast values(3,'Time is one of the greatest mystery','Sarah long','15:26','D:\\podcast\\time.wav',17,'social');
insert into podcast values(4,'Music and The Human Brain','Jim Teddar','15:15','D:\\podcast\\human&music.wav',18,'psychology');
update podcast set location='D:\\podcast\\time.wav' where podcast_id=3;

select * from podcast;

create table all_playlist
(
id int not null primary key,
playlist_name varchar(40) not null,
create_date date not null
);

insert into all_playlist values(31,'Fun Songs','2021-06-30');
insert into all_playlist values(32,'Favourites','2021-05-20');
select * from all_playlist;

create table playlist_detail
(
id int not null primary key,
item_id int not null,
list_type varchar(20) not null,
album_id int not null,
all_playlist_id int not null,
constraint fk_playlist_id foreign key(all_playlist_id) references all_playlist(id)
);


select location from song s join playlist_detail pd on s.song_id=pd.item_id where pd.item_id=1 and all_playlist_id=67;
select @itemid:=song_id from song where song_name='who says';
insert into playlist_detail values(101,@itemid,'song',12,32);
select * from playlist_detail;
select * from playlist_detail where all_playlist_id =(select id from all_playlist where playlist_name='tt');
select item_id from playlist_detail where all_playlist_id =(select id from all_playlist where playlist_name='tt') and list_type='song';
select song_name from song s join playlist_detail pd on s.song_id=pd.item_id where all_playlist_id =(select id from all_playlist where playlist_name='tt') and list_type='song';
select * from song order by album_name; 
select * from song order by genre;