import { Routes } from "@angular/router";
import { RegisterComponent } from "./register/register.component";
import { HomeComponent } from "./home/home.component";

export const routes: Routes = [
    { title: 'Home', path: '', component: HomeComponent },
    { title: 'Register', path: 'register', component: RegisterComponent }
];
