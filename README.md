# Reproducer for Hibernate issue HHH-15212

If you have defined a HBM database-object with a ${schema}-placeholder, the placeholder is replaced with an empty string instead of the configured schema (config file) since Hibernate version 5.6.2.

See https://hibernate.atlassian.net/browse/HHH-15212