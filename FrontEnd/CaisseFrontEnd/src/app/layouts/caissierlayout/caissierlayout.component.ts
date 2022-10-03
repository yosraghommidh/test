import { Component, OnInit, ViewChild } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { filter, map, shareReplay, delay } from 'rxjs/operators';
import { UserService } from 'src/app/services/user.service';
import {AuthenticationService} from 'src/app/services/authentication.service'
import { NavigationEnd, Router } from '@angular/router';
import { MatSidenav } from '@angular/material/sidenav';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
@UntilDestroy()
@Component({
  selector: 'app-caissierlayout',
  templateUrl: './caissierlayout.component.html',
  styleUrls: ['./caissierlayout.component.css']
})
export class CaissierlayoutComponent  implements OnInit {
  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;

  UserName! : any ;


  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
  constructor(private breakpointObserver: BreakpointObserver, private userService: UserService,
    private router : Router , private AuthenticationService : AuthenticationService) {}
    public getUser(){
      const jwt=this.AuthenticationService.getToken();
      console.log("jhjjj",jwt);
      return this.userService.getUserNameJWT(jwt).subscribe(
        (response) => {
          
          console.log("uppppp",response);

          this.UserName=response;
          console.log("User" , this.UserName);
        }
      );
      
    }
  ngOnInit(): void {
    this.getUser();
    console.log("User" , this.UserName);
    
  }
  goToProfile() {
    this.router.navigate(['/caissier/', 'userProfile']);
  }
  ngAfterViewInit() {
    this.breakpointObserver
      .observe(['(max-width: 800px)'])
      .pipe(delay(1), untilDestroyed(this))
      .subscribe((res) => {
        if (res.matches) {
          this.sidenav.mode = 'over';
          this.sidenav.close();
        } else {
          this.sidenav.mode = 'side';
          this.sidenav.open();
        }
      });

    this.router.events
      .pipe(
        untilDestroyed(this),
        filter((e) => e instanceof NavigationEnd)
      )
      .subscribe(() => {
        if (this.sidenav.mode === 'over') {
          this.sidenav.close();
        }
      });
  }
  public logout()
  {
    this.AuthenticationService.clear() 
    this.router.navigate(['/home']);
  }
}
