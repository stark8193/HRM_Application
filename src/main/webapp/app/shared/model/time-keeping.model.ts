import dayjs from 'dayjs';
import { IEmployee } from 'app/shared/model/employee.model';

export interface ITimeKeeping {
  id?: number;
  date?: string;
  checkIn?: string;
  checkOut?: string | null;
  employee?: IEmployee | null;
}

export const defaultValue: Readonly<ITimeKeeping> = {};
