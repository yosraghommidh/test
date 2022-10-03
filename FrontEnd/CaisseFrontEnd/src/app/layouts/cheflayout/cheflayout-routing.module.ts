import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from 'src/app/components/home/home.component';
import { DashboardComponent } from 'src/app/Pages/dashboard/dashboard.component';
import { FacttableComponent } from 'src/app/Pages/facttable/facttable.component';
import { SessionComponent } from 'src/app/Pages/session/session.component';
import { UserProfileComponent } from 'src/app/Pages/user-profile/user-profile.component';
import { CheflayoutComponent } from './cheflayout.component';

const routes: Routes = [
  {path : 'home', component : HomeComponent,},
    { 
      path: 'chef', component: CheflayoutComponent , children: [
      {path : 'userProfile', component : UserProfileComponent},
      {path : 'dashboard', component : DashboardComponent},
      {path : 'facture' , component : FacttableComponent},
      {path : 'session' , component : SessionComponent}

     ]},]
  ;
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CheflayoutRoutingModule { }
