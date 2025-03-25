import { Routes } from '@angular/router';
import { ListRestaurantsComponent } from './list-restaurants/list-restaurants.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';

export const routes: Routes = [
    {path: 'restaurants', component:ListRestaurantsComponent},
    {path: 'adminpanel', component:AdminPanelComponent},
    {path: '', redirectTo:'home', pathMatch:'full'},
    {path: '**', component:NotFoundComponent}
];
