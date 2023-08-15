import { Component, OnInit } from '@angular/core';
import { NamestajService } from '../services/namestaj.service';
import { NamestajModel } from '../models/namestaj.model';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css'],
})
export class CreateComponent implements OnInit {
  public naziv: string;
  public cena: string;
  public boja: number;
  public slika: string;
  // public materijal: string;

  constructor(public namestajService: NamestajService) {}

  ngOnInit(): void {}
  // tslint:disable-next-line:typedef
  async create() {
    await this.namestajService.create(
      this.naziv,
      this.cena,
      this.slika,
      this.boja /*, this.materijal*/
    );
  }
}
