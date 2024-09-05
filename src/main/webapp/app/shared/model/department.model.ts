import { IEmployee } from 'app/shared/model/employee.model';

export interface IDepartment {
  id?: number;
  departName?: string;
  shortName?: string | null;
  employees?: IEmployee[] | null;
}

export const defaultValue: Readonly<IDepartment> = {};
