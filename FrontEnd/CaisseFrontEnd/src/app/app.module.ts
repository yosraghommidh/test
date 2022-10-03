import { BrowserModule } from '@angular/platform-browser';
/* Routing */
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
/* Angular Material */
import { NgModule  } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './angular-material.module';
/* FormsModule */
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
/* Angular Flex Layout */
import { FlexLayoutModule } from '@angular/flex-layout';
/* Components */
import { LoginComponent } from './components/login/login.component';

import { HttpClientModule  } from '@angular/common/http';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { RouterModule } from '@angular/router';
import { ComponentsModule } from './components/components.module';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { LayoutModule } from '@angular/cdk/layout';
import { CaissierlayoutModule } from './layouts/caissierlayout/caissierlayout.module';
import { MatDialogModule } from '@angular/material/dialog';
import { CheflayoutModule } from './layouts/cheflayout/cheflayout.module';
import { AdminlayoutModule } from './layouts/adminlayout/adminlayout.module';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
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
    CaissierlayoutModule,
    MatDialogModule,
    CheflayoutModule,
    AdminlayoutModule


  ],
  providers: [],
  bootstrap: [AppComponent],


})
export class AppModule { }
