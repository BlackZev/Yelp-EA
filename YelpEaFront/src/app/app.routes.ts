import { Routes } from '@angular/router';
import { ListRestaurantsComponent } from './list-restaurants/list-restaurants.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
    {path: 'restaurants', component:ListRestaurantsComponent},
    {path: 'adminpanel', component:AdminPanelComponent},
    {path: 'login', component:LoginComponent},
    {path: '', redirectTo:'restaurants', pathMatch:'full'},
    {path: '**', component:NotFoundComponent}
];
