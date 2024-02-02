create database if not exists TickelFilm;
use TickelFilm;
drop table Ghe;
create table if not exists Phim (
	PhimID int primary key,
	Ten_Phim nvarchar(255),
	Loai_Phim nvarchar(255),
	Thoi_Gian int);
    
create table if not exists Phong (
PhongID int primary key,
Ten_Phong nvarchar(255),
Trang_Thai bit);


create table if not exists Ghe (
	GheID int primary key,
	PhongID int,
	So_ghe varchar(25));

alter table Ghe 
add foreign key (PhongID) references Phong (PhongID);

create table if not exists Ve (
	PhimID int,
	GheID int,
	Ngay_chieu datetime,
	Trang_thai nvarchar(255));
    
 alter table Ve
 add foreign key (PhimID) references Phim(PhimID);
 
  alter table Ve
 add foreign key (GheID) references Ghe(GheID);
 
 insert into Phim (PhimID, Ten_Phim, Loai_Phim, Thoi_Gian)values(1,'Em Bé Hà Nội', 'Tâm Lý', 90);
 insert into Phim (PhimID, Ten_Phim, Loai_Phim, Thoi_Gian)values(2,'Nhiệm Vụ Bất Khả Thi', 'Hành Động', 100);
 insert into Phim (PhimID, Ten_Phim, Loai_Phim, Thoi_Gian)values(3,'Dị Nhân', 'Viễn Tưởng', 90);
 insert into Phim (PhimID, Ten_Phim, Loai_Phim, Thoi_Gian)values(4,'Cuốn Theo Chiều Gió', 'Tình Cảm', 120);
 
 insert into Phong (PhongID, Ten_Phong, Trang_Thai) values( 1,'Phòng Chiếu 1',1);
 insert into Phong (PhongID, Ten_Phong, Trang_Thai) values( 2,'Phòng Chiếu 2',1);
 insert into Phong (PhongID, Ten_Phong, Trang_Thai) values( 3,'Phòng Chiếu 3',0);
 
 insert into Ghe (GheID, PhongID, So_Ghe) values (1,1,'A3');
 insert into Ghe (GheID, PhongID, So_Ghe) values (2,1,'B5');
 insert into Ghe (GheID, PhongID, So_Ghe) values (3,2,'A7');
 insert into Ghe (GheID, PhongID, So_Ghe) values (4,2,'D1');
 insert into Ghe (GheID, PhongID, So_Ghe) values (5,3,'T2');
 
 insert into Ve (PhimID, GheID, Ngay_Chieu, Trang_Thai) values (1,1,'2008-10-20','Đã Bán');
 insert into Ve (PhimID, GheID, Ngay_Chieu, Trang_Thai) values (1,3,'2008-11-20','Đã Bán');
 insert into Ve (PhimID, GheID, Ngay_Chieu, Trang_Thai) values (1,4,'2008-12-23','Đã Bán');
 insert into Ve (PhimID, GheID, Ngay_Chieu, Trang_Thai) values (2,1,'2009-02-14','Đã Bán');
 insert into Ve (PhimID, GheID, Ngay_Chieu, Trang_Thai) values (3,1,'2009-02-14','Đã Bán');
 insert into Ve (PhimID, GheID, Ngay_Chieu, Trang_Thai) values (2,5,'2009-03-08','Chưa');
 insert into Ve (PhimID, GheID, Ngay_Chieu, Trang_Thai) values (2,3,'2009-03-08','Chưa');
 
 

select* from phim;