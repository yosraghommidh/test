import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { CaissierlayoutComponent } from './layouts/caissierlayout/caissierlayout.component';
import { DashboardComponent } from './Pages/dashboard/dashboard.component';
import { UserProfileComponent } from './Pages/user-profile/user-profile.component';
const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent},
  { path: 'caissier', component: CaissierlayoutComponent , children: [
    {path : 'userProfile', component : UserProfileComponent},
    {path : 'dashboard', component : DashboardComponent}
   ]},
 
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }