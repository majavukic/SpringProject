import { Component, OnInit } from '@angular/core';
import { NamestajService } from '../services/namestaj.service';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';
import { NamestajModel } from '../models/namestaj.model';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  public naziv: string;
  public id: string;
  namestaj: Observable<NamestajModel[]>;
  // Za azuriranje
  // public materijal: string;
  public cena: string;
  public boja: string;
  public slika: string;

  constructor(
    private route: ActivatedRoute,
    private namestajService: NamestajService,
    public authService: AuthService
  ) {}

  async ngOnInit(): Promise<void> {
    this.id = this.route.snapshot.paramMap.get('id');
    if (this.id) {
      this.namestaj = await this.namestajService.getOneById(this.id);
    } else {
      this.namestaj = await this.namestajService.getAll();
    }
    console.log(this.namestaj);
    await this.getNamestaj();
  }

  // tslint:disable-next-line:typedef
  async getNamestaj() {
    this.namestaj.pipe(take(1)).subscribe((namestaj) => {
      console.log('Pipe: ', namestaj);
    });
  }
  // tslint:disable-next-line:typedef
  async delete(id) {
    await this.namestajService.delete(id);
  }
  // tslint:disable-next-line:typedef
  async getByName() {
    console.log('Naziv: ', this.naziv);
    this.namestaj = this.namestaj.pipe(
      map((subject) => subject.filter((komad) => komad.naziv === this.naziv))
    );
  }
  // tslint:disable-next-line:typedef
  async edit(id) {
    await this.namestajService.update(
      this.naziv,
      // this.materijal,
      this.cena,
      this.slika,
      this.boja,
      id
    );
  }
}
