alter session set nls_date_format = 'DD-MM-YYYY HH24:MI:SS';

create table Client(
	idClient varchar(20),
	nomTarif varchar(10),
	nom varchar(15),
	prenom varchar(20),
	adresse varchar(30),
	numComptElec varchar(20),
	categorie varchar(15),
	argent decimal
);

/*
create table Prelevement(
	idPrelevement varchar(20),
	indexCompteurElec integer,
	ancientIndexElec integer,
	datePrelevement date,
	consommationElec integer,
	numComptElec varchar(20)
); */

-- AVEC ETAT
create table Prelevement(
	idPrelevement varchar(20),
	indexCompteurElec integer,
	ancientIndexElec integer,
	datePrelevement date,
	consommationElec integer,
	numComptElec varchar(20),
	etat integer
);


create table PrelevementFacture(
	idClient varchar(20),
	idPrelevement varchar(20),
	idFacture varchar(20)
);


create table Tarif(
	nomTarif varchar(10),
	prixUnitaireElec decimal,
	prixUnitaireElec2 decimal,
	prixUnitaireElec3 decimal,
	limiteElec integer,
	limiteElec2 integer
);

create table Facture(
	idFacture varchar(20),
	idClient varchar(20),
	idPrelevement varchar(20),
	dateFacture date,
	moisFacture varchar(10),
	anneeFacture varchar(10),
	agence varchar(15),
	consommationElec integer,
	montantElec decimal,
	montantElec2 decimal,
	montantElec3 decimal,
	montantTotal decimal
);
/*
	tranche
		id
		tranche
		qte min
		qte max
*/
create sequence idClient;
create sequence idPrelevement;
create sequence idPrelevementfact;
create sequence idFacture;

/* client */
insert into client values (idClient.nextval,'16','Rambeloson','Andry','IIB32A Amboditsiry','56789','Particulier',500000.00);
insert into client values (idClient.nextval,'12','Ra','Fanantenana','Antananarivo','12345','Administration',800000.00);
insert into client values (idClient.nextval,'16','Ramarokoto','Rado','Andoaranofotsy','22222','Particulier',2000000.00);
insert into client values (idClient.nextval,'16','zah','zah','Andoaranofotsy','33333','Particulier',2000000.00);

/*prelevement*/
-- insert into prelevement values (idPrelevement.nextval,50,10,'12-10-2018',40,'56789');

/*prelevementfacture*/
insert into prelevementfacture values ('1','1','1');
insert into prelevementfacture values ('1','2','2');

/*tarif*/
insert into tarif values ('16',1000,1500,2000,100,150);
insert into tarif values ('12',1500,2000,4000,150,200);

/*facture*/
insert into facture values('1','1','1','17-10-2018','OCT','2018','ITU',120,100,150,160,45000);
insert into facture values(idFacture.nextval,'1','34','15-10-2018','OCT','2018','ITU',120,100,150,160,45000);
insert into facture values(idFacture.nextval,'1','35','15-10-2018','OCT','2018','ITU',120,100,150,160,45000);
insert into facture values(idFacture.nextval,'1','36','15-10-2018','OCT','2018','ITU',120,100,150,160,45000);
insert into facture values(idFacture.nextval,'1','37','15-10-2018','OCT','2018','ITU',120,100,150,160,45000);

/*view*/
create view prelfact as select prelevement.idPrelevement from PrelevementFacture join prelevement on PrelevementFacture.idPrelevement=prelevement.idPrelevement; 

create sequence idPrepaidCompter;
-- duree en jour
create table PrepaidCompter(
	idPrepaidCompter varchar(20) primary key,
	prix decimal(10,2),
	quantite integer,
	duree integer
);

insert into PrepaidCompter values(
	idPrepaidCompter.nextval,
	150000,
	30,
	30
);

insert into PrepaidCompter values(
	idPrepaidCompter.nextval,
	100000,
	20,
	30
);

insert into PrepaidCompter values(
	idPrepaidCompter.nextval,
	200000,
	50,
	30
);

insert into PrepaidCompter values(
	idPrepaidCompter.nextval,
	75000,
	15,
	30
);

create sequence idCompteur;

create table compteur(
	idCompteur varchar(20) primary key,
	numComptElec varchar(20),
	idClient varchar(20),
	idPrepaidCompter varchar(20),
	dateAchat date,
	dateExpiration date,	
	foreign key(idPrepaidCompter) references PrepaidCompter(idPrepaidCompter)
);

insert into compteur values('15','56789','1','1','','');


create table FactureAvoir(
	idFactureAvoir varchar(20) primary key,
	idFacture varchar(20),
	montant decimal,
	dateFactureAvoir date,
	typeAnnulation integer
);

create table PrelevementAnnulee(
	idPrelevementAnnulee varchar(20) primary key,
	idPrelevement varchar(20),
	datePrelevementAnnulee date
);

create sequence idFactureAvoir;
create sequence idPrelevementAnnulee;

/*
	drop table Client;
	drop table Prelevement;
	drop table Tarif;
	drop table Facture;
	drop table PrelevementFacture;
	drop table PrepaidCompter;
	drop table Compteur;
	drop sequence idPrepaidCompter;
	drop sequence idCompteur;
	drop sequence idClient;
	drop sequence idPrelevement;
	drop sequence idFacture;
	drop sequence idPrelevementfact;
*/