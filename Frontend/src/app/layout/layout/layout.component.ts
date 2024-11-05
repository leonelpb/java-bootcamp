import { Component } from '@angular/core';
import { NavbarComponent } from "../../shared/navbar/navbar.component";
import { ProductListComponent } from "../../features/product-list/product-list.component";
import { FooterComponent } from "../../shared/footer/footer.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [NavbarComponent, ProductListComponent, FooterComponent,CommonModule],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss'
})
export default class LayoutComponent {

}
