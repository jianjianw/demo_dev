create table music(
  id varchar(36) primary key,
  sno int,
  type varchar(36),
  author varchar(36),
  name varchar(36),
  createTime timestamp
);


insert into music values
                        ('1',1,'类型1','作者1','名字1','2018-10-02'),
                        ('2',2,'类型2','作者2','名字2','2018-10-02'),
                        ('3',3,'类型3','作者3','名字3','2018-10-02'),
                        ('4',4,'类型4','作者4','名字4','2018-10-02'),
                        ('6',6,'类型5','作者5','名字5','2018-10-06'),
                        ('7',7,'类型7','作者7','名字7','2018-10-07')
WHERE not exists (select * from music where id='7');


insert into music values
                        ('1',1,'类型1','作者1','名字1','2018-10-02'),
                        ('2',2,'类型2','作者2','名字2','2018-10-02'),
                        ('3',3,'类型3','作者3','名字3','2018-10-02'),
                        ('4',4,'类型4','作者4','名字4','2018-10-02'),
                        ('6',6,'类型5','作者5','名字5','2018-10-06'),
                        ('7',7,'类型7','作者7','名字7','2018-10-07')
ON DUPLICATE KEY UPDATE
  sno =  VALUES(sno),
  type =  VALUES(type),
  author =  VALUES(author),
  name =  VALUES(name),
  createTime =  VALUES(createTime);