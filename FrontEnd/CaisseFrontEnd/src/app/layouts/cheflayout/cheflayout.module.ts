import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ComponentsModule } from 'src/app/components/components.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LayoutModule } from '@angular/cdk/layout';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from 'src/app/Pages/dashboard/dashboard.component';
import { UserProfileComponent } from 'src/app/Pages/user-profile/user-profile.component';
import { CheflayoutComponent } from './cheflayout.component';
import { CheflayoutRoutingModule } from './cheflayout-routing.module';
import { SessionComponent } from 'src/app/Pages/session/session.component';



@NgModule({
  declarations: [
    CheflayoutComponent,
    SessionComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    CheflayoutRoutingModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    FormsModule, 
    ReactiveFormsModule,
    AngularMaterialModule,
    HttpClientModule,
    RouterModule,
    ComponentsModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    LayoutModule,
  ],
  providers: [],
  bootstrap: [CheflayoutComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CheflayoutModule { }
