import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Agent } from 'src/app/interfaces/agent';
import { Caisse } from 'src/app/interfaces/caisse';
import { Session } from 'src/app/interfaces/session';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { FactureService } from 'src/app/services/facture.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.css']
})
export class SessionComponent implements OnInit {

  constructor(public factService : FactureService ,  public userServ : UserService, 
    public authServ : AuthenticationService ,
    private router : Router ,  private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.showsession()
    this.ngAfterViewInit()
    this.initform()
    this.userServ.getUserNameJWT(this.authServ.getToken()).subscribe(
      (r:any) => {
        this.agent =r 
      }
    )
  }
  agents : any[] = []
  hide : boolean = false
  caisses! :FormControl;
  caisseData: Caisse[] =[];
  agentsform!: FormControl;
  agentdata: Agent[]=[]
  c! : any
  a! : any
  mnt! : any
  start!: any
  end! : any 
  guichet! : any 
  Agent! : any
  HideS : boolean = false
  HideJ : boolean = true
  displayedColumns: string[] = ['idcaisse','agent', 'initial', 'total' , 'start','end' , 'action' ];
  public sessiondata : Session[]= []
  dataSource = new MatTableDataSource<Session>(this.sessiondata);
  @ViewChild(MatPaginator) paginator!: MatPaginator ;
  ngAfterViewInit() {
    this.dataSource.data=this.sessiondata;
    this.dataSource.paginator = this.paginator;
  }

  initform(){
    this.caisses=this.formBuilder.control('')
    this.agentsform=this.formBuilder.control('')
    this.factService.getAvailAgent().subscribe(
      (r : any ) =>{
        this.agentdata=r

      })
      this.factService.getAvailCaisse().subscribe(
        (resp : any ) =>{
          this.caisseData=resp
  
        })
  }
  session! : any
  showsession()
  {
    this.sessiondata=[]
    this.factService.getSessions().subscribe(
      (resp : any ) =>{
        console.log("reeeee", resp)
        var j : number =0
        this.sessiondata=resp ;
        this.session = this.sessiondata.length 
        this.guichet= "20 Caisses"
        this.dataSource.data=this.sessiondata;
        for (var i = 0; i < resp.length; i++){
            this.mi+=resp[i].mnt_initial
            this.mt+=resp[i].mnt_initial

          this.factService.getAgent(resp[i].ageid).subscribe(
            (r : any ) =>{
              this.agents.push(r)

            })
        }

      }
    )
  }
agent : any 
  public   getAg(id : any){
     this.factService.getAgent(id).subscribe(
      (r : any ) =>{
         this.agent =r
      })

      return this.agent
  }

  openSess(){
    console.log("hereeee")
    this.hide=true
  }

  openJ(){
    console.log("hereeee")
    this.HideS=true
    this.HideJ=false
  }
  closeJ(){
    console.log("hereeee")
    this.HideS=false
    this.HideJ=true
  }

ajouterSession(){
  console.log("a", this.a , "c", this.c , "mnt" , this.mnt ,
   "start" , this.start , "end " , this.end)
   
   this.factService.getCaisse(this.c).subscribe(
    (r : any ) =>{
      console.log("caisseaaaaaaa", r)
      this.factService.saveSession(this.a, this.mnt , this.start, this.end , r).subscribe(
        (resp : any ) =>{
          this.sessiondata=[]
          this.factService.getSessions().subscribe(
            (resp : any ) =>{
              var j : number =0
              this.sessiondata=resp ;
              this.session = this.sessiondata.length 
              this.guichet= "20 Caisses"
              this.dataSource.data=this.sessiondata;
              for (var i = 0; i < resp.length; i++){
                  this.mi+=resp[i].mnt_initial
                  this.mt+=resp[i].mnt_initial
      
                this.factService.getAgent(resp[i].ageid).subscribe(
                  (r : any ) =>{
                    this.agents.push(r)
      
                  })
              }
      
            }
          )        })
    })
    
    this.hide=false
    //window.location.reload();

    }

    mi : number =0
    mt : number =0
}
