import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from 'src/app/components/home/home.component';
import { DashboardComponent } from 'src/app/Pages/dashboard/dashboard.component';
import { FacttableComponent } from 'src/app/Pages/facttable/facttable.component';
import { UserProfileComponent } from 'src/app/Pages/user-profile/user-profile.component';
import { CaissierlayoutComponent } from './caissierlayout.component';

const routes: Routes = [
{path : 'home', component : HomeComponent,},
  { 
    path: 'caissier', component: CaissierlayoutComponent , children: [
    {path : 'userProfile', component : UserProfileComponent},
    {path : 'dashboard', component : DashboardComponent},
    {path : 'facture' , component : FacttableComponent},

   ]},]
;
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CaissierlayoutRoutingModule { }
