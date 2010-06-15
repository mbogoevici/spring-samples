drop table Doctor if exists;
drop table Client if exists;
drop table Patient if exists;
drop table Appointment if exists;
drop table Species if exists;
drop table Breed if exists;

create table Doctor (	id identity, 
						firstName varchar(255) not null, 
						lastName varchar(255) not null, 
						primary key (id));

create table Client (	id identity, 
						firstName varchar(255) not null, 
						lastName varchar(255) not null,
						phone varchar(255) not null,
						street varchar(255) not null,
						apartment varchar(255),
						city varchar(255) not null,
						state varchar(255) not null,
						zip varchar(255) not null,
						country varchar(255) not null,
						primary key (id));

create table Patient (	id identity,
						clientId bigint not null,
						name varchar(255) not null,
						gender char not null,
						species varchar(255) not null,
						breed varchar(255) not null,
						birthDate date not null,
						weight decimal not null,
						doctorId bigint not null,						
						primary key (id),
						foreign key (clientId) references Client(id),
						foreign key (doctorId) references Doctor(id));

create table Appointment (	id identity, 
							dateTime datetime not null,
							patientId bigint not null,
						  	doctorId bigint not null,
						  	reason varchar(255),
						  	primary key (id),
							foreign key (patientId) references Patient(id),
							foreign key (doctorId) references Doctor(id));	

create table Species (	id identity, 
						name varchar(255) not null,
						primary key (id));

create table Breed (	id identity, 
						name varchar(255) not null,
						speciesId bigint not null,
						primary key (id),
						foreign key (speciesId) references Species(id));
