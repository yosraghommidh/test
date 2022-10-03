import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from 'src/app/components/home/home.component';
import { AgentsComponent } from 'src/app/Pages/agents/agents.component';
import { DashboardComponent } from 'src/app/Pages/dashboard/dashboard.component';
import { UserProfileComponent } from 'src/app/Pages/user-profile/user-profile.component';
import { AdminlayoutComponent } from './adminlayout.component';

const routes: Routes = [
  {path : 'home', component : HomeComponent,},
    { 
      path: 'admin', component: AdminlayoutComponent , children: [
      {path : 'userProfile', component : UserProfileComponent},
      {path : 'dashboard', component : DashboardComponent},
      {path : 'gestUsers' , component : AgentsComponent},
   

     ]},
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminlayoutRoutingModule { }
