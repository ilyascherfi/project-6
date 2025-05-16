import { Routes } from "@angular/router";
import { RegisterComponent } from "./register/register.component";
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
export const routes: Routes = [
  { title: 'Home', path: '', component: HomeComponent },
  { title: 'Register', path: 'register', component: RegisterComponent },
  { title: 'Login', path: 'login', component: LoginComponent }
];
