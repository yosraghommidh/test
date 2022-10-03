import { NgModule,CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminlayoutRoutingModule } from './adminlayout-routing.module';
import { AdminlayoutComponent } from './adminlayout.component';
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
import { AgentsComponent } from 'src/app/Pages/agents/agents.component';


@NgModule({
  declarations: [
    AdminlayoutComponent,
    AgentsComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AdminlayoutRoutingModule,
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
  bootstrap: [AdminlayoutComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdminlayoutModule { }
