
```python
import sys
import csv
import re


class FindMatches:
    def __init__(self, input_file, matching_type):
        self.matching_type = matching_type.lower()
        if self.matching_type not in ['email', 'phone', 'email_phone']:
            print("Please use a valid matching type: 'email', 'phone', or 'email_phone'.")

        self.input_file = input_file
        self.rownum = 0
        self.header = None
        self.email_col_2 = None
        self.email_col = None
        self.phone_col_2 = None
        self.phone_col = None

        self.ids = {}
        self.id = 1

        csv_file_in = open(self.input_file, 'rU')
        reader = csv.reader(csv_file_in)

        for row in reader:
            if self.rownum == 0:
                self.header = row
                self.rownum += 1
                break

        for col in range(len(self.header)):
            if 'email2' in self.header[col].lower():
                self.email_col_2 = col
            if 'email1' in self.header[col].lower() or 'email' == self.header[col].lower():
                self.email_col = col

        for col in range(len(self.header)):
            if 'phone2' in self.header[col].lower():
                self.phone_col_2 = col
            if 'phone1' in self.header[col].lower() or 'phone' == self.header[col].lower():
                self.phone_col = col

        self.write_csv(reader, self.header)

    def email_match(self, row, min_id):
        row_id = None

        if min_id:
            iden = min_id
        else:
            iden = self.id

        if self.email_col_2:
            if row[self.email_col_2]:
                email2 = row[self.email_col_2]
                self.add_key_to_dict(email2, iden)

                if self.ids.get(email2):
                    row_id = self.ids.get(email2)

        if row[self.email_col]:
            email1 = row[self.email_col]
            self.add_key_to_dict(email1, iden)
            row_id = self.ids.get(email1, self.id)

        if row_id is None:
            row_id = self.id

        return row_id

    def format_phone(self, row, column):
        format_phone_col = re.sub('\D+', '', row[column])

        if len(format_phone_col) > 10:
            format_phone_col = format_phone_col[1:]

        return format_phone_col

    def phone_match(self, row, min_id):
        row_id = None

        if min_id:
            iden = min_id
        else:
            iden = self.id

        if self.phone_col_2:
            if row[self.phone_col_2]:
                ids_key = self.format_phone(row, self.phone_col_2)
                self.add_key_to_dict(ids_key, iden)

                if self.ids.get(ids_key):
                    row_id = self.ids.get(ids_key)

        if row[self.phone_col]:
            ids_key = self.format_phone(row, self.phone_col)
            self.add_key_to_dict(ids_key, iden)
            row_id = self.ids.get(ids_key, self.id)

        if row_id is None:
            row_id = self.id

        return row_id

    def add_key_to_dict(self, ids_key, iden):
        self.ids[ids_key] = self.ids.get(ids_key, iden)

    def write_csv(self, reader, header, row_is_header=True):
        csv_file_out = open('output_file.csv', 'w')
        writer = csv.writer(csv_file_out)

        while row_is_header:
            header = ['id'] + self.header
            writer.writerow(header)
            row_is_header = False

        for row in reader:
            row_id = None