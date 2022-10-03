import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Client } from 'src/app/interfaces/Client';
import { Compte } from 'src/app/interfaces/compte';
import { Facture } from 'src/app/interfaces/facture';
import { Organisation } from 'src/app/interfaces/organisation';
import { SearchType } from 'src/app/interfaces/Searchtype';
import { Session } from 'src/app/interfaces/session';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { FactureService } from 'src/app/services/facture.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-facttable',
  templateUrl: './facttable.component.html',
  styleUrls: ['./facttable.component.css']
})
export class FacttableComponent implements AfterViewInit , OnInit {
moderegl:number =1
Agid!: number ;
searchCtrl!: FormControl;
searchTypeCtrl!: FormControl;
SerachForm: FormGroup | any;
HideClient: Boolean = false ; 
isDisabled: boolean = true ;
clientF! : Client ;
caisses! :FormControl;
caisseData: Session[] =[];
OpType!: FormControl;
OrgType!: FormControl;
ModeRType! : FormControl;
Comptes! :FormControl;
ComptesData : Compte[] = [] ;
solde! : number ;
Orgs: Organisation[]= [];
idFactP!: any ;
montantPayer! : number ;
exced! : number
reste!: number
rendu! : number
check!: boolean
checktab: boolean[]=[]
checkrendu : boolean = false
soldeL!: number;
ResteL! : number;
soldeLundef!: number;
ResteLundef! : number;
verser:number=0
encaiss! : number
today = Date.now();
dateNow! : any ;
searchTypeOptions!: {
  value: SearchType,
  label: string
}[];
displayedColumns: string[] = ['Ref','train', 'type','Date', 'DateE','CRef',  'Org' ,
 'montant'
  ,'Dette', 'Encaissement',    
 'compte'  , 'MontV' ,'Action' ];
public factureData : Facture[] = [] ;
dataSource = new MatTableDataSource<Facture>(this.factureData);
types: any = [
  { value: '1', viewValue: 'Payement Facture' 
  },
];
ModesR: any = [
  { value: '1', viewValue: 'Espèces' 
  },
  { value: '2', viewValue: 'Chèque' },
  { value: '3', viewValue: 'Encaissement libre' }
];
constructor(public factService : FactureService ,  public userServ : UserService, 
  public authServ : AuthenticationService ,
  private router : Router ,  private formBuilder: FormBuilder) {} 

  ngOnInit(): void {
    this.initForm();
    this.getdatenow();
    this.HideClient=false; 
   }
  public refresh()
  {
   window.location.reload();
  }
    @ViewChild(MatPaginator) paginator!: MatPaginator;
  
    ngAfterViewInit() {
      this.dataSource.paginator = this.paginator;
    }

 getdatenow(){
  var today = new Date();
  var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
  var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
  var dateTime = date+' '+time;
this.dateNow= dateTime ;
}

  set =setInterval(() => {
  this.getdatenow();
}, 1000);




  private initForm() {
    this.Comptes=this.formBuilder.control('');
    this.ModeRType=this.formBuilder.control('');
    this.searchCtrl = this.formBuilder.control('');
    this.caisses = this.formBuilder.control('');
    this.OpType = this.formBuilder.control('');
    this.OrgType = this.formBuilder.control('');

    this.searchTypeCtrl = this.formBuilder.control(SearchType.Facture);
    this.searchTypeOptions = [
        { value: SearchType.Facture, label: 'Facture' },
        { value: SearchType.Client, label: 'Client' },
        { value: SearchType.Compte, label: 'Compte' }
    ];
}

public search (searchCtrl: any , searchTypeCtrl : any )
{
  this.Comptes=this.formBuilder.control('');
  this.ModeRType=this.formBuilder.control('');
  this.caisses = this.formBuilder.control('');
  this.OpType = this.formBuilder.control('');
  this.OrgType = this.formBuilder.control('');
    if(searchTypeCtrl.value=="fact_id")
  {
    return this.factService.showByFactRef(searchCtrl.value).subscribe(
      (response) => {       
        this.factureData= [];
        this.factureData=response;
        if (response==null)
        this.factureData= [];

        this.dataSource.data=this.factureData;
      }
    );
  }
  else if(searchTypeCtrl.value=="client_id")
  {
    return this.factService.showByClientRef(searchCtrl.value).subscribe(
      (response) => {      
        this.factureData=response ;
        if (response==null)
        this.factureData= [];
        this.dataSource.data=this.factureData;
      }
    );
  }
  else if(searchTypeCtrl.value=="compte_id")
  {
    return this.factService.showByCompteRef(searchCtrl.value).subscribe(
      (response) => {      
        this.factureData=response ;
        if (response==null)
        this.factureData= [];
        this.dataSource.data=this.factureData;
      }
    );
  }
  else return null;
}
pay : boolean = false
public payer_Fact (i : number  ){

  this.userServ.getAgentJWT(this.authServ.getToken()).subscribe(
    (response) => {
      console.log("hereeeee", response)
        this.factService.GetCaisseAvail(response).subscribe(
          (resp) => {
            console.log("caissseeeesssss",resp)
            this.caisseData.push(resp);
            console.log("caissseeeesssss",this.caisseData)
            
          }
        ) ;
    }
  ) ;
  this.caisses = this.formBuilder.control('');
  this.ModeRType = this.formBuilder.control(this.ModesR[0].value);
  this.OpType = this.formBuilder.control(this.types[0].value);

  this.isDisabled=false ;
  console.log("typeeeeeeeeeee",this.types)
  const id : any = this.factureData[i].deb_id ;
  this.Orgs.push(this.factureData[i].org_ID)
  this.OrgType = this.formBuilder.control(this.factureData[i].org_ID.org_id);
  this.montantPayer=this.factureData[i].deb_amountinit -this.factureData[i].deb_AMOUNT_CASH
  this.idFactP=id ;
  this.factService.GetClientByFactId(id).subscribe(
    (response) => {
      console.log("client",response);
      this.clientF=response ;
      this.HideClient=true;
    }
  ) ;
  this.factService.showByFactId(this.idFactP).subscribe(
    (response) => {       
      this.factureData= [];
      this.factureData.push(response)
      this.dataSource.data=this.factureData;
    }
  );
  this.factService.GetComptesByFactId(id).subscribe(
    (response) => {
      this.checkrendu=true
      console.log("Comptes",response);
      this.ComptesData=response ;
      console.log("accccc",this.ComptesData)
        this.factService.GetAccountById(this.ComptesData[0].aco_ref, this.idFactP).subscribe(
        (response : any)=>{
          console.log("acccidddddd" , response)
          this.solde=response.aco_amount
          this.Comptes = this.formBuilder.control(this.ComptesData[0].aco_id);
          this.exced=response.aco_amount-this.montantPayer
          this.reste=this.montantPayer+this.exced-this.solde
          this.rendu=this.verser-this.montantPayer
     
        }
        )
    }
  ) ;
  
}

public getSolde( value : any ){
console.log("vaaaaa", value)
var idAcc: any
this.factService.GetAccountById(value, this.idFactP).subscribe(
  (response : any)=>{
    console.log("acccidddddd" , response)
    this.solde=response.aco_amount
    idAcc=response.aco_id
    console.log("aciiiiiiiiid" , idAcc)
    const f : Facture = this.factureData[0]
    this.factService.GetDebtAccId(idAcc).subscribe(
      (response : any)=>{
        console.log("aciiiiiiiiid1111" , idAcc)
        const O : Organisation[] =this.Orgs
        this.factureData= [];
        this.factureData.push(f)
        this.factureData.push(response)
        this.dataSource.data=this.factureData;
        this.Orgs=[]
        this.Orgs.push(response.org_ID)
        O.forEach(v =>{
          this.Orgs.push(v)
        })
        console.log("orgg",this.Orgs)
        this.OrgType = this.formBuilder.control(response.org_ID.org_id);       
      }
    )
  }
  )
}

getverser()
{
  this.rendu=this.verser-this.montantPayer
  if (this.rendu>=0)
  {
    this.encaiss=this.montantPayer;
  }
  else{
    this.encaiss=this.verser;
  }
  console.log("verserrrrr" , this.verser, "encaiss" , this.encaiss)
}
public payer(i : any  , check : any)
{
  if (check.target.checked==true){
    this.payer_Fact(i)
    this.check=true
    check.target.checked=true
  }
  else 
  {
    this.check=false
this.refresh()
  }

}

public checked(i : any)
{
  console.log("checkeedddddddddddd", this.checktab[i])
  alert("ok")
  alert()
}

public Encaisser(){
  console.log("encaisser", this.solde , this.exced, this.rendu , this.Comptes.value)
  console.log(this.factureData)

  this.factService.payerFact(this.factureData[0].deb_id,  this.encaiss ).subscribe(
    (response )=>{
      console.log("payeeeeeeeeee", response)
      this.pay=true
      this.factureData=[]
      this.factureData.push(response)
      this.dataSource.data=this.factureData;
    }
  )
  this.userServ.getAgentJWT(this.authServ.getToken()).subscribe(
    (response) => {
      this.Agid=response;
      console.log("rrrrrrrrrrrrrrrrrr", response)
      console.log("rrrrrrrrrrrrrrrrrr", this.Agid)


   
  
    console.log("viewwwwwwwww",this.encaiss)
 this.factService.EncaisPayFact(this.encaiss,this.Agid,this.ModesR[this.moderegl-1].viewValue,
  this.caisseData[0].css_id,this.factureData[0].deb_id,this.clientF).subscribe(
    (response) => {
      console.log("rrrrrrrrrrrrrrrrrr", response)
    });
  });
}
changeLibre(event : any)
{
  console.log("eeeeeeeeeeeeeeeeezzzz",this.ModesR[this.moderegl-1].viewValue, this.moderegl)
  
  if (event==3)
  {
    this.soldeL=this.solde
    this.ResteL=this.exced
    this.checkrendu=false
  }
  else{
    this.soldeL=this.soldeLundef
    this.ResteL=this.ResteLundef
    this.checkrendu=true
  }
}


}



