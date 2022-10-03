import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { SearchType } from 'src/app/interfaces/Searchtype';
import { UserProfile } from 'src/app/interfaces/UserProfile';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { FactureService } from 'src/app/services/facture.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.css']
})
export class AgentsComponent implements OnInit {

  constructor(public factService : FactureService ,  public userServ : UserService, 
    public authServ : AuthenticationService ,
    private router : Router ,  private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.showusers()
    this.searchCtrl = this.formBuilder.control('');
    this.searchTypeCtrl = this.formBuilder.control(SearchType.Facture);
    this.searchTypeOptions = [
        { value: SearchType.Facture, label: 'Réference' },
        { value: SearchType.Client, label: 'Nom' },
        { value: SearchType.Compte, label: 'Role' }
    ];

      }
      searchCtrl!: FormControl;
      searchTypeCtrl!: FormControl;
      searchTypeOptions!: {
        value: SearchType,
        label: string
      }[];
  displayedColumns: string[] = ['ref','nom', 'profil', 'action'  ];
  public agentdata : UserProfile[]= []
  dataSource = new MatTableDataSource<UserProfile>(this.agentdata);
  @ViewChild(MatPaginator) paginator!: MatPaginator ;
  ngAfterViewInit() {
    this.dataSource.data=this.agentdata;
    this.dataSource.paginator = this.paginator;
  }
  prof : string[] = []

  showusers()
  {
    this.factService.getUsers().subscribe(
      (resp : any) =>{
        console.log("iddddd",resp[0].dis_ADMPROFILE[0].dis_PRU_LABEL)

        this.agentdata=resp
        this.dataSource.data=this.agentdata
        var j =0
        do{
         this.factService.getprofile(this.agentdata[j].dis_USE_ID).subscribe(
          (r : any) =>{
            //console.log("iddddd",this.agentdata[j].dis_USE_ID )
            this.prof.push(r)
          }
        )
        j++
        }
        while (j <this.agentdata.length)
        
      }
    )

  }
  deleteup (id : any)
  {       
    console.log("heeeeeeeeeey" )

    console.log("supppp" , id)
    this.factService.deleteup(id).subscribe(
      (r : any) =>{
        console.log("rrrrrrrrrrrrrr" , r)

      }
    )
    this.showusers()
  }

}
