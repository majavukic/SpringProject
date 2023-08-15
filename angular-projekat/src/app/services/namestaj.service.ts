import { Injectable } from '@angular/core';
import axios from 'axios';
import { NamestajModel } from '../models/namestaj.model';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NamestajService {
  constructor() {}

  // tslint:disable-next-line:typedef
  async getAll(): Promise<Observable<NamestajModel[]>> {
    const url = 'http://localhost:8080/projekatit355/namestaji';
    try {
      const res = await axios.get(url, {});
      const data = res.data;
      return of(data);
      // console.log('Phones: ', data);
      // return data;
    } catch (e) {
      console.log('Error: ', e);
    }
  }

  // tslint:disable-next-line:typedef
  async getOneById(id): Promise<Observable<NamestajModel[]>> {
    const url = 'http://localhost:8080/projekatit355/namestaji/';
    try {
      const res = await axios.get(url + id);
      const data = [res.data];
      console.log('Namestaj: ', data);
      return of(data);
    } catch (e) {
      console.log('Error: ', e);
    }
  }

  // tslint:disable-next-line:typedef
  async create(naziv, /*materijal,*/ cena, slika, boja) {
    const url =
      'http://localhost:8080/projekatit355/vrstaNamestaja/1/namestaji';
    try {
      const res = await axios.post(url, {
        naziv,
        /*materijal,*/ cena,
        slika,
        boja,
      });
      const data = res.data;
      console.log('Added: ', data);
      return data;
    } catch (e) {
      console.log('Error: ', e);
    }
  }

  // tslint:disable-next-line:typedef
  async update(naziv, /*materijal,*/ cena, slika, boja, id) {
    const url = 'http://localhost:8080/projekatit355/namestaji/' + id;
    try {
      const res = await axios.put(url, {
        naziv,
        /*materijal,*/ cena,
        slika,
        boja,
      });
      const data = res.data;
      console.log('Updated: ', data);
      return data;
    } catch (e) {
      console.log('Error: ', e);
    }
  }

  // tslint:disable-next-line:typedef
  async delete(id) {
    const url = 'http://localhost:8080/projekatit355/namestaji/' + id;
    try {
      const res = await axios.delete(url, { data: { id } });
      const data = res.data;
      console.log('Phones: ', data);
      return data;
    } catch (e) {
      console.log('Error: ', e);
    }
  }
}
